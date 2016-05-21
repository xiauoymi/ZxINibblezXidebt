;nibblecontact = {
	weeklyChart : null,
	name 	: 'nibblecontact',
	
	init 	: function () {
		$('#home-content').load('views/home/'+nibblecontact.name+'.html', function(){
			//init stuff
		});
	}
};