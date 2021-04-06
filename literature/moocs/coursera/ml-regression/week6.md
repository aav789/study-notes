# Week 6: Nearest Neighbours & Kernel Regression

## Pre class notes:

* Class is about using "nonparametric" methods to fit data.
  * From Wikipedia: a non parametric method "does not require modeller to make any assumptions about the distribution of the population"
* Simplest example is "nearest neighbour regression".
  * Predictions for a single point is based on "most related" observations.
* Kernel regression is another example.
  * Similar to nearest neighbour but uses all observations weights by similarity to query point.

## Notes

* Limitations of parametric regression
  * A polynomial fit might work well in certain regions of input space and not well in others.
    * Eg cubic fit might work well for higher square feet houses, but not so well for lower.
  * Ideal method:
    * Flexible enough to support "local structure" ie different fit at certain input space regions.
    * Doesn't require us to infer "structure breaks" ie the places where we want a different fit.

* Nearest neighbour regression approach
  * For some input, find the closest observation. That's it.
  * What people do naturally when predicting house prices.
  * Formally:
	1. For some input, find closest $$ \mathbf{x}_i $$ using some distance metric.
	2. Predict: $$ \hat{y}_q = y_{nn} $$

* Distance metrics
	* 1D feature sets: Euclidian distance
		* $$ distance(x_j, x_q) = |x_j - x_q| $$ 
	* Multi dimensions:
		* Can use a weight on each feature to determine importance in computing distance ie sqft closeness is important, but year renovated may not be.
		* $$  distance(\mathbf{x_j}, \mathbf{x_q}) = \sqrt{a_1(x_i[1] - x_q[1])^2) + .. a_D(x_i[D] - x_q[D])^2)} $$   
			* Note: $$ a_D $$ is the "weight" in this equation.

* 1-Nearest Neighbour Algorithm
	* Need to define your distance function.
	* In pseudo:

		```
		closest_distance = float('inf')

		for i in all_data_points:
			dist = calculate_distance(i, input)
           if dist < closest_distance:
                closest_distance = dist

		return closest_distance
 			
		``` 
	* Notes:
		* Sensitive to regions with missing data; need heaps of data for it to be good.
		* Sensitive to noisy datasets.

* k-Nearest neighbours regression
	* Same as 1-Nearest Neighbour but average over values of "k"-nearest neighbours.

* k-Nearest neighbours in practise
	* Usually has a much better fit than 1-nearest.
	* Issues in regions where there's sparse data and at boundaries.

* Weighted k-nearest neighbours
	* Idea: weight neighbours by how close or far they are to data point.
	* Formula (where $$ C_{qNN1} $$ refers to some weight for NN1:
		$$ \hat{y}_q = \dfrac{C_{qNN1}Y_{qNN1} + C_{qNN2}Y_{qNN2} .. C_{qNNK}Y_{qNNK}}{\sum\limits_{j=1}^{K}C_{qNNj}} $$
	* Weighting data points:
		* Could just use inverse of distance: $$ C_{qNN1} = \dfrac{1}{distance(\mathbf{X_j}, \mathbf{X_q})} $$
		* Can use "kernel" functions:
			* Gaussian kernel
			* Uniform kernel etc

* Kernel regression
	* Instead of weighing n-neighbours, weigh all points with some "kernel":
		$$ \hat{y}_q \frac{\sum\limits_{y=1}^{N} C_{qi}Y_{qi}}{\sum\limits_{y=1}^{N} C_{qi}} = \frac{\sum\limits_{y=1}^{N} kernel_{\lambda}(distance(\mathbf{x_i},\mathbf{x_q})) * y_i}{kernel_{\lambda}(distance(\mathbf{x_i},\mathbf{x_q}))} $$
	* In stats called "Nadaya-Watson" kernel weighted averages.
	* Need to choice a good "bandwidth" (lambda value)
		* Too high = over smoothing; low variance, high bias.
		* Too low = over fitting; high variance, high bias.   
	* Need to choose kernel but bandwidth more important.
	* Use validation set (if enough data) or cross-validation to choose $$ \lambda $$ value.

* Global fits of parametric models vs local fits of kernel regression
  * If you were to predict datapoint by averaging all observations, you'd end up with a constant fit.
  * Kernel gives constant fit at a single point; a "locally constant fit".
