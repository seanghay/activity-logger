## Activity Logger

Print all activity lifecycles inside your application

### But Why?

It's a library for inspecting Activity/Fragment lifecycles correspond to the current screen inside Android project. It's useful when we're new to the project and want to find Activity or Fragment classes quicker.


### Add JitPack

`build.gradle` (project level)

```diff
repositories {
    google()
    jcenter()
+   maven { url "https://jitpack.io" }
}
```


### Installation

`app/build.gradle` (app level)

```groovy
dependencies {
  debugImplementation 'com.github.seanghay:activity-logger:1.0.0'
  debugImplementation "androidx.startup:startup-runtime:1.0.0"
}
```


### How to use

Just search for tag `ActivityLogger` inside your Logcat.

I will look something similar to this

```
I/ActivityLogger: Activity: MainActivity [Resumed] - 08:30:25 PM
I/ActivityLogger: Activity: MainActivity -> Fragment: SimpleFragment [Attached] - 08:30:25 PM
I/ActivityLogger: Activity: MainActivity -> Fragment: SimpleFragment [Created] - 08:30:25 PM
I/ActivityLogger: Activity: MainActivity -> Fragment: SimpleFragment [ViewCreated] - 08:30:25 PM
I/ActivityLogger: Activity: MainActivity -> Fragment: SimpleFragment [ActivityCreated] - 08:30:25 PM
I/ActivityLogger: Activity: MainActivity -> Fragment: SimpleFragment [Attached] - 08:30:25 PM
I/ActivityLogger: Activity: MainActivity -> Fragment: SimpleFragment [Created] - 08:30:25 PM
I/ActivityLogger: Activity: MainActivity -> Fragment: SimpleFragment [ViewCreated] - 08:30:25 PM
I/ActivityLogger: Activity: MainActivity -> Fragment: SimpleFragment [ActivityCreated] - 08:30:25 PM
I/ActivityLogger: Activity: MainActivity [Destroyed] - 08:30:26 PM
I/ActivityLogger: Activity: MainActivity [Paused] - 08:31:22 PM
I/ActivityLogger: Activity: MainActivity [Stopped] - 08:31:23 PM
I/ActivityLogger: Activity: MainActivity [SaveInstanceState] - 08:31:23 PM
```
