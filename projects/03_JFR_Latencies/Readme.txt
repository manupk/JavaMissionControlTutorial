This example features a logger that is shared between a bunch of worker threads.
It's easier to just use the recordings provided.

1. The idea with the worker threads is to get work done in parallel. How many 
   threads are actually running in parallel? Why?
   
2. Can you change the application by simply removing a key word to change this?

3. Can you think of another way to change the application to do this?

Help below if you get stuck.



































1. Go to the Events | Graph tab. Green is good - the thread is running. Red is bad, the thread is waiting on entering a Java monitor.

2. As can be seen, only one thread at a time is getting to run. The application is doing work in a sequential fashion, one thread at a time.

3. The blocking events all seem to be due to calls to the Log method. See Threads | Contention tab.

4. They are actually sharing the exact same lock instance. See Threads | Lock Instances.

5. There are a few ways to fix this available to us. We can cut the call to the logger altogether. If the logging doesn’t use a shared resource, 
   we can just remove the synchronization from the log method. We could also provide each thread with its own logger, carefully making sure that 
   we do not end up blocking on another shared resource instead. Of course, this is just an example, and no such limitations exist - any of these
   solutions should do.

5. New recording after the fix will show lot's of green in parallel. All red is gone.