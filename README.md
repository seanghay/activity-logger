## Activity Logger

Print all activity lifecycles inside your application

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
