;nibblestart = {
	weeklyChart : null,
	name 	: 'nibblestart',
	
	init 	: function () {
		$('#getstarted-content').load('views/home/'+nibblestart.name+'.html', function(){
			$('#getstarted_form_modal').modal('show');
		});
	}
};