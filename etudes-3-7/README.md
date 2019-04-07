Usage
-----

After opening a browser tab pointing at [localhost:3449](http://localhost:3449/), select from two
radio buttons—either the one with the dropdown list with corresponding pre-set latitude values, or
the second one with the input box, then press the `Calculate` button to compute the average amount
of daylight in a month for the selected latitude value:

![image](/etudes-3-7/images/image.png)

![image](/etudes-3-7/images/image-0.png)

To fiddle with the namespace of `src/etudes/core.cljs`, run the following inside the REPL:

```
(in-ns 'etudes.core)
(require 'etudes.core :reload)
```

After making changes to `src/etudes/core.cljs`, re-run `(require 'etudes.core :reload)`.
