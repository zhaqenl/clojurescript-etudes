etudes-stats
============


Overview
--------

This is a solution for [Étude 3-3: More List Manipulation](http://catcode.com/etudes-for-clojurescript/ch03-lists-vectors.html).


Usage
-----

After opening a browser tab pointing at [localhost:3449](http://localhost:3449/), execute the
following steps in order to get the mean, median, and the standard deviation of a given list of
numbers:

1. Input numbers separated by a space.
3. Press the _Show statistics_ button to display the mean, median, and the standard deviation.

![image](/images/image.png)

To fiddle with the namespace of `src/etudes/core.cljs`, run the following inside the REPL:

```
(in-ns 'etudes.core)
(require 'etudes.core :reload)
```

After making changes to `src/etudes/core.cljs`, re-run `(require 'etudes.core :reload)`.
