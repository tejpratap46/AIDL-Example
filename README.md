# AIDL Example

## AIDL needs 3 things:

1. An AIDL Interface.
2. An AIDL service app (service host)
3. An AIDL client app (service client)

### Creating an AIDL Interface

Steps:

1. Enable build feature in Android Studio.
```groovy
    buildFeatures {
        aidl = true
    }
```
2. Create a new AIDL file using File -> New -> AIDL -> AIDL File
3. Let's name it `ICalculator.aidl`
4. Declare aidl methods:
```aidl
    interface IExampleAidlInterface {
        int add(int a, int b);
    }
```

### Create AIDL server

An AIDL server is an app that does the actual work and sends back the result to requesting clients.
One service can connects with multiple clients via Binders.

Steps:

1. Create a service, e.g. `CalculatorService extends Service`
2. Create an Implementation of `ICalculator.Stub`, lets name it `CalculatorImpl`
3. Implement methods:

```kotlin
   object CalculatorImpl : ICalculator.Stub() {
    override fun add(a: Int, b: Int): Int {
        return a + b
    }
}
```

4. Expose your stub implementation with service binder:

```kotlin
   class CalculatorService : Service() {
    override fun onBind(intent: Intent): IBinder {
        return CalculatorImpl
    }
}
```

Now your service is ready to be consumed by your clients, now let's create a client application.

### Create AIDL client

An AIDL client is an app that binds to an AIDL service, calls API, shows response inside its own
views.

Steps:

1. Create a service connection:

```kotlin
   private var aidlService: ICalculator? = null

   private val serviceConnection = object : ServiceConnection {
    override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
        aidlService = ICalculator.Stub.asInterface(service)
        Toast.makeText(applicationContext, "Service Connected", Toast.LENGTH_LONG).show()
    }

    override fun onServiceDisconnected(name: ComponentName?) {
        aidlService = null
        Toast.makeText(applicationContext, "Service Disconnected", Toast.LENGTH_LONG).show()
    }
   }
```

2. Connect to AIDL service via Binders.

```kotlin
   val serviceIntent = Intent()
   serviceIntent.component = ComponentName(BuildConfig.AIDL_PACKAGE, BuildConfig.AIDL_SERVICE)

   bindService(serviceIntent, serviceConnection, BIND_AUTO_CREATE)
```

3. Call methods of AIDL after successful connection:
```kotlin
aidlService?.add(2, 2)?.let {
    Toast.makeText(
        applicationContext,
        String.format(Locale.getDefault(), "Sum is: %d", it),
        Toast.LENGTH_LONG
    ).show()
}
```