import android.Manifest
import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.AddCircle
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.lukitateam.lukita.data.response.ArtResponse
import com.lukitateam.lukita.ui.common.UiState
import com.lukitateam.lukita.ui.screen.camera.CameraViewModel
import com.lukitateam.lukita.util.createFile
import com.lukitateam.lukita.util.reduceFileImage
import com.lukitateam.lukita.util.rotateFile
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


@Composable
fun CameraScreen(
    viewModel: CameraViewModel = viewModel(),
    navController: NavController,
    onNavigate: () -> Unit,
) {
    val shouldShowCamera = remember { mutableStateOf(false) }
    val context = LocalContext.current

    var isResponseSuccess by remember { mutableStateOf(false) }

    val scope = rememberCoroutineScope()
    val state = viewModel.predictState.collectAsState(initial = null)
    var prediction by remember { mutableStateOf<UiState<ArtResponse>>(UiState.Loading) }

    val cameraExecutor = Executors.newSingleThreadExecutor()

    val requestPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            Log.i("permission", "Permission granted")
            shouldShowCamera.value = true
        } else {
            Log.i("permission", "Permission denied")
        }
    }

    RequestCameraPermission(context, shouldShowCamera, requestPermissionLauncher)

    if (shouldShowCamera.value) {
        CameraView(
            executor = cameraExecutor,
            onImageCaptured = { file ->
                rotateFile(file, true)
                val reducedFile = reduceFileImage(file)
                val imageMultipart: MultipartBody.Part = MultipartBody.Part.createFormData(
                    "file",
                    file.name,
                    reducedFile.asRequestBody("image/*".toMediaTypeOrNull())
                )

                scope.launch {
                    prediction = viewModel.predict(imageMultipart)

                    when (val response = prediction) {
                        is UiState.Success -> {
                            navController.currentBackStackEntry?.savedStateHandle?.set(
                                key = "artResponse",
                                value = response.data
                            )
                            navController.currentBackStackEntry?.savedStateHandle?.set(
                                key = "file",
                                value = file.path
                            )
                            isResponseSuccess = true
                        }

                        is UiState.Error -> {
                            val errorMessage = response.errorMessage
                            Log.e("CameraScreen", errorMessage)
                        }

                        is UiState.Loading -> {
                            Log.i("CameraScreen", "loading")
                        }
                    }

                }
            },
            onError = { Log.e("Camera", "View error:", it) }
        )
    }


    LaunchedEffect(key1 = state.value?.isError) {
        scope.launch {
            if (state.value?.isError?.isNotBlank() == true) {
                val error = state.value?.isError
                Toast.makeText(context, "$error", Toast.LENGTH_LONG).show()
            }
        }
    }

    if (state.value?.isLoading == true) {
        CircularProgressIndicator()
    }

    if (isResponseSuccess) {
        onNavigate()
        isResponseSuccess = false
    }

    DisposableEffect(Unit) {
        onDispose {
            cameraExecutor.shutdown()
        }
    }
}

@Composable
fun CameraView(
    executor: Executor,
    onImageCaptured: (File) -> Unit,
    onError: (ImageCaptureException) -> Unit,
) {
    val lensFacing = CameraSelector.LENS_FACING_BACK
    val context = LocalContext.current
    val application = context.applicationContext as Application
    val lifecycleOwner = LocalLifecycleOwner.current

    val preview = Preview.Builder().build()
    val previewView = remember { PreviewView(context) }
    val imageCapture: ImageCapture = remember { ImageCapture.Builder().build() }
    val cameraSelector = CameraSelector.Builder().requireLensFacing(lensFacing).build()

    LaunchedEffect(lensFacing) {
        val cameraProvider = context.getCameraProvider()
        cameraProvider.unbindAll()
        cameraProvider.bindToLifecycle(
            lifecycleOwner, cameraSelector, preview, imageCapture
        )

        preview.setSurfaceProvider(previewView.surfaceProvider)
    }

    Box(contentAlignment = Alignment.BottomCenter, modifier = Modifier.fillMaxSize()) {
        AndroidView({ previewView }, modifier = Modifier.fillMaxSize())

        IconButton(modifier = Modifier.padding(bottom = 20.dp), onClick = {
            Log.i("camera", "ON CLICK")
            takePhoto(
                imageCapture = imageCapture,
                application = application,
                executor = executor,
                onImageCaptured = onImageCaptured,
                onError = onError
            )
        }, content = {
            Icon(
                imageVector = Icons.TwoTone.AddCircle,
                contentDescription = "Take picture",
                tint = Color.White,
                modifier = Modifier
                    .size(100.dp)
                    .padding(1.dp)
                    .border(1.dp, Color.White, CircleShape)
            )
        })
    }
}

@Composable
private fun RequestCameraPermission(
    context: Context,
    shouldShowCamera: MutableState<Boolean>,
    requestPermissionLauncher: ActivityResultLauncher<String>,
) {
    LaunchedEffect(Unit) {
        when {
            ContextCompat.checkSelfPermission(
                context, Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED -> {
                Log.i("permission", "Permission previously granted")
                shouldShowCamera.value = true
            }

            ActivityCompat.shouldShowRequestPermissionRationale(
                context as Activity, Manifest.permission.CAMERA
            ) -> Log.i("permission", "Show camera permissions dialog")

            else -> {
                requestPermissionLauncher.launch(Manifest.permission.CAMERA)
            }
        }

    }
}

private fun takePhoto(
    imageCapture: ImageCapture,
    application: Application,
    executor: Executor,
    onImageCaptured: (File) -> Unit,
    onError: (ImageCaptureException) -> Unit,
) {

    val photoFile = createFile(application)

    val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()

    imageCapture.takePicture(outputOptions, executor, object : ImageCapture.OnImageSavedCallback {
        override fun onError(exception: ImageCaptureException) {
            Log.e("camera", "Take photo error:", exception)
            onError(exception)
        }

        override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
            onImageCaptured(photoFile)
        }
    })
}

private suspend fun Context.getCameraProvider(): ProcessCameraProvider =
    suspendCoroutine { continuation ->
        ProcessCameraProvider.getInstance(this).also { cameraProvider ->
            cameraProvider.addListener({
                continuation.resume(cameraProvider.get())
            }, ContextCompat.getMainExecutor(this))
        }
    }