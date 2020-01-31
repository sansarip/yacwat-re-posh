## Development Mode

```
lein clean
lein fig:dev
```

Our pal [Figwheel Main](https://figwheel.org/) or *Figgy Smalls* as I like to call it will spin your application right up for you with auto-refresh capabilties n' all!

Wait a bit, then browse to [http://localhost:9500](http://localhost:9500).

#### Devcards

You can view your devcards at [http://localhost:9500/devcards.html](http://localhost:9500/devcards.html).

#### Testing

Want to test your cljs? Visit [http://localhost:9500/figwheel-extra-main/auto-testing](http://localhost:9500/figwheel-extra-main/auto-testing) and see your test turn from red to green!

## Production Build

```
lein clean
lein prod
```

That should compile the ClojureScript code first, and then create the standalone jar which you can then run with `java -jar target/my-datascript-example.jar`

*Good luck, and have fun, you rascal Clojurians!* :beers:
