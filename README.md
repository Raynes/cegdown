# cegdown

Cegdown is a simple little Clojure wrapper for the excellent
[Pegdown](https://github.com/sirthias/pegdown) markdown parsing library.

## Usage

You'll want to require it first:

```clojure
user> (require '[me.raynes.cegdown :as md])
nil
```

Now you can generate some HTML from a bit of markdown:

```clojure
user> (md/to-html "# hi\nhow are you")
"<h1>hi</h1><p>how are you</p>"
```

Pegdown has lots of
[extensions](http://www.decodified.com/pegdown/api/org/pegdown/Extensions.html). You
can use them with `to-html`. Just pass it a sequence of keywords. For example, if
we wanted fenced code blocks (like this README uses):

```clojure
user> (md/to-html "```clojure\n(+ 3 3)\n```" [:fenced-code-blocks])
"<pre><code class=\"clojure\">(+ 3 3)\n</code></pre>"
```

Easy enough, right?

## License

Copyright © 2012 Anthony Grimes

Distributed under the Eclipse Public License, the same as Clojure.
