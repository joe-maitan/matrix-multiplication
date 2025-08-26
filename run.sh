# !/bin/bash

gradle clean build --parallel --daemon --build-cache --configuration-cache --no-continue
java -cp build/classes/java/main/ csx55.threads.MatrixThreads $1 $2
