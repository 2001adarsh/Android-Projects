#### The error you get when performing two task at the same time. ->
- Skipped 240 frames!  The application may be doing too much work on its main thread.

#### When the UI THREAD/ MAIN THREAD is the general thread where all the tasks of the program gets excetuted one by one. But this leads to a problem. For example, If the system is made to wait for 10seconds, then for those 10sec the main thread gets blocked, by that I mean that no other user can actually do any task at that particular amount of time. Even if user has set some instructions to be performed at that particular time, they will be executed after waiting for 10 sec only.

For this purpose we make certain task to be done in a different thread, this process is called Asynchronous Programming.
TO achieve this, there are various ways:
 - Handler (android.os.Handler)
 - Async Task


## Handlers :
```
 Handler handler = new Handler();
            Runnable r = new Runnable() {
                @Override
                public void run() {
                    layout.setBackgroundColor(Color.RED);
                    Log.d(String.valueOf(123), "We have waited 5 sec");
                }
            };
            handler.postDelayed(r,5000);
```            
## AsynTask:
AsyncTask enables proper and easy use of the UI thread. This class allows
you to perform background operations and publish results on the UI thread
without having to manipulate threads and/or handlers.

To use AsyncTask, we create a class that extends from asynctask. We can call
execute on the object of the class to start the work in different thread.
An AsyncTask streamlines the following common background process:
1. Pre - Execute code on the UI thread before starting a task (e.g show
ProgressBar)
2. Backgroud - Run a background task on a thread given certain inputs
(e.g fetch data)
© 2018 Coding Blocks, Ebook by Coding Blocks
2/3
3. Updates - Display progress updates during the task (optional)
4. Post - Execute code on UI thread following completion of the
background task (e.g show data). This take in the argument, work done
by the background thread.



```
    start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CountTask countTask =  new CountTask();
                countTask.execute(5);
            }
        });
```

```
 class CountTask extends AsyncTask<Integer, Integer, Void>{

        @Override
        protected Void doInBackground(Integer... integers) {
            Log.d("At", "Background Work started");
            int n = integers[0];
            //waitNsec(n);
            for(int i=0; i<n; i++){
                wait1sec();
                publishProgress(i);
            }
            Log.d("At", "Background Work ended");
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            textView.setText(String.valueOf(values[0]));
        }
    }

    private void wait1sec(){
        long cur = System.currentTimeMillis();
        while(System.currentTimeMillis() < cur + 1000);
    }
 ```   
