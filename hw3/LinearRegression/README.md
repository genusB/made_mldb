### To run project from the console:

`cd` into `LinearRegression` folder

Then run `sbt`. This should open up the sbt console.

After in sbt console run:

<pre><code>~run "./Fish_train.csv" "./Fish_test.csv" "./results.csv"</code></pre>

Validation task was unclear for me, so I decided to log metrics with step 25 during learning
process into `./metrics.txt` file.