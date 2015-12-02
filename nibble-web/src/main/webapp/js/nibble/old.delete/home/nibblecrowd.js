;nibblecrowd = {
	weeklyChart : null,
	name 	: 'nibblecrowd',
	
	init 	: function () {
		$('#home-content').load('views/home/'+nibblecrowd.name+'.html', function(){
			//init stuff
		});
	}
};