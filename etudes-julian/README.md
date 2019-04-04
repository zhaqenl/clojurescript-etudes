etudes-julian
=============


Overview
--------

This is a solution for [Étude 3-2: More List Manipulation](http://catcode.com/etudes-for-clojurescript/ch03-lists-vectors.html).


Usage
-----

After opening a browser tab pointing at [localhost:3449](http://localhost:3449/), execute the
following steps in order to get the amount of daylight (in hours and minutes) of a given latitude
and date:

1. Under _Latitude_, input a float value.
2. Input a date with a _yyyy-mm-dd_ format.
3. Press the _Calculate_ button to display the amount of daylight in hours and minutes.

![image](/etudes-julian/images/image.png)

To fiddle with the namespace of `src/etudes/core.cljs`, run the following inside the REPL:

```
(in-ns 'etudes.core)
(require 'etudes.core :reload)
```

After making changes to `src/etudes/core.cljs`, re-run `(require 'etudes.core :reload)`.


Note
----

If the result displays `NaN hours NaN minutes`, the provided latitude could not be handled correctly
by the utilized formula provided [here](http://mathforum.org/library/drmath/view/56478.html)
(the effective input latitude that can be handled by the formula is between _-80_ to _82_).
