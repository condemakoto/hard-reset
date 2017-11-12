pkg load nnet;
data = load("customers.csv");
loans = load("loans.csv");

#normalize data
mu = mean(data);
sigma = std(data);
data_norm = (data - mu) ./ sigma;

#pick only the wanted features to consider, so remove the ids...
data_norm = data_norm(:, 2:8);

#get the min and the max of all the features.
mm = [min(data_norm); max(data_norm)];

#create the neural network
#net = newff(mm',[7, 21, 100, 50, 1]);
net = newff(mm',[14, 21, 10, 1]);
	# Train network
[net] = train(net, data_norm', loans');
out = sim(net, data_norm')
#out = sim(net, data_norm')