Usage
-----

After opening a browser tab pointing at [localhost:3449](http://localhost:3449/), input numbers
separated with a single space to get their mean, median, and standard deviation, in real time.

![image](/etudes-3-3/images/image.png)

To fiddle with the namespace of `src/etudes/core.cljs`, run the following inside the REPL:

```
(in-ns 'etudes.core)
(require 'etudes.core :reload)
```

After making changes to `src/etudes/core.cljs`, re-run `(require 'etudes.core :reload)`.
