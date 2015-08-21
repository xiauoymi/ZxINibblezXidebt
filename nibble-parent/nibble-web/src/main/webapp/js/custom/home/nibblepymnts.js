;nibblepymnts = {
	weeklyChart : null,
	name 	: 'nibblepymnts',
	
	init 	: function () {
		$('#home-content').load('views/home/'+nibblepymnts.name+'.html', function(){
			//init stuff
		});
	}
};