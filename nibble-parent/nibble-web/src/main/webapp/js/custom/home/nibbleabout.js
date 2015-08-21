;nibbleabout = {
	weeklyChart : null,
	name 	: 'nibbleabout',
	
	init 	: function () {
		$('#home-content').load('views/home/'+nibbleabout.name+'.html', function(){
			//init stuff
		});
	}
};