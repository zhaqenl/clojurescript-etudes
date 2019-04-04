Usage
-----

The `generate-pockets` function accepts two arguments—the first one being a string containing a
single or multiple `T`’s and `F`’s which correspond to an existing or absent tooth, respectively;
and the second one being the chance of having a healthy existing tooth.

The following are examples of running the function inside the REPL:

```
dev:etudes.core=> (generate-pockets "TTFF" 0.9)
[[2 3 2 2 3 2] [1 1 1 1 1 1] [] []]
dev:etudes.core=> (generate-pockets "TTFF" 0.9)
[[2 3 2 2 3 3] [3 3 1 1 1 1] [] []]
dev:etudes.core=> (generate-pockets "TTFF" 0.9)
[[1 1 2 3 1 2] [2 1 1 3 3 3] [] []]
dev:etudes.core=> (generate-pockets "TTFF" 0.9)
[[2 4 4 3 3 1] [1 1 2 1 1 1] [] []]
dev:etudes.core=> (generate-pockets "TTFF" 0.9)
[[3 1 2 1 3 1] [3 3 1 1 2 3] [] []]
dev:etudes.core=> (generate-pockets "TTFF" 0.9)
[[2 2 2 2 2 2] [1 3 1 2 3 3] [] []]
dev:etudes.core=> (generate-pockets "TTFF" 0.9)
[[2 3 1 1 1 1] [2 3 2 1 3 1] [] []]
dev:etudes.core=> (generate-pockets "TTFF" 0.9)
[[3 2 3 3 1 1] [2 2 2 2 2 3] [] []]
```
