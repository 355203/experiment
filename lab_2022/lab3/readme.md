# 实验三  实现基本的图像分类APP

## 一、向应用中添加TensorFlow Lite

1.选择"start"模块

![](../images/lab3/1.png)

2.右键“start”模块，或者选择File，然后New>Other>TensorFlow Lite Model

![](../images/lab3/2.png)

3.选择已经下载的自定义的训练模型。本教程模型训练任务以后完成，这里选择finish模块中ml文件下的FlowerModel.tflite。

![](../images/lab3/3.png)

点击“Finish”完成模型导入，系统将自动下载模型的依赖包并将依赖项添加至模块的build.gradle文件

4.最终TensorFlow Lite模型被成功导入，并生成摘要信息

![](../images/lab3/4.png)

## 二、检查代码中的TODO项

1.首先查看TODO列表视图，View>Tool Windows>TODO

![](../images/lab3/5.png)

2.默认情况下了列出项目所有的TODO项，进一步按照模块分组

![](../images/lab3/6.png)

## 三、添加代码重新运行APP

1.定位“start”模块**MainActivity.kt**文件的TODO 1，添加初始化训练模型的代码

```
private class ImageAnalyzer(ctx: Context, private val listener: RecognitionListener) :
        ImageAnalysis.Analyzer {

  ...
  // TODO 1: Add class variable TensorFlow Lite Model
  private val flowerModel = FlowerModel.newInstance(ctx)

  ...
}
```

2.在CameraX的analyze方法内部，需要将摄像头的输入`ImageProxy`转化为`Bitmap`对象，并进一步转化为`TensorImage` 对象

```
override fun analyze(imageProxy: ImageProxy) {
  ...
  // TODO 2: Convert Image to Bitmap then to TensorImage
  val tfImage = TensorImage.fromBitmap(toBitmap(imageProxy))
  ...
}
```

3.对图像进行处理并生成结果

```
override fun analyze(imageProxy: ImageProxy) {
  ...
  // TODO 3: Process the image using the trained model, sort and pick out the top results
  val outputs = flowerModel.process(tfImage)
      .probabilityAsCategoryList.apply {
          sortByDescending { it.score } // Sort with highest confidence first
      }.take(MAX_RESULT_DISPLAY) // take the top results

  ...
}
```

4.将识别的结果加入数据对象`Recognition` 中，包含`label`和`score`两个元素。后续将用于`RecyclerView`的数据显示

```
override fun analyze(imageProxy: ImageProxy) {
  ...
  // TODO 4: Converting the top probability items into a list of recognitions
  for (output in outputs) {
      items.add(Recognition(output.label, output.score))
  }
  ...
}
```

5.将原先用于虚拟显示识别结果的代码注释掉或者删除

```
// START - Placeholder code at the start of the codelab. Comment this block of code out.
for (i in 0..MAX_RESULT_DISPLAY-1){
    items.add(Recognition("Fake label $i", Random.nextFloat()))
}
// END - Placeholder code at the start of the codelab. Comment this block of code out.
```

## 三、运行效果

![](../images/lab3/7.png)