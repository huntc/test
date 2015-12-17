memory test
===

To run:

```
sbt
> run 
```

...then attach a profiler.

When profiling memory you should see `AbstractNodeQueue$Node` objects survive many generations of GC (I noticed
that they survived several), after a few minutes of running. Sorting the profiler's output by the # of 
generations also helps see what is kept around the longest.