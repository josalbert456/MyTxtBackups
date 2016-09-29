Customize our own TextView may be better: CustomTextView.java

MyViewerOne.java: a very raw and full-of-bugs view

Maybe the best way to padding character type text is not to padding, but simply assign all characters, even a space included, fixed width, which is very easy to implemented and good for performance

TextViewCustomColorPos.java: customized color can be set at customized position, one page can just set once, maybe a better implementation is to set multiple times, a new class to contain colorSet information should be defined in the customTextview class and a container should defined and contain this information 

TextColorInfo.java is used for multiple-setting text colors in one screen page. In the CustomTextView class, we use ArrayList to store all setTextColor information, and use an index to iterate them before assign their value to the textLength, textStart, textColor values and before we draw the last end color line. Though we have implemented it, there are still some problems.
