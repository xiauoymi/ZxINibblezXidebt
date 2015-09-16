'use strict';
app.factory("pwdstrength", function() {
   function getStrength(pass) {
       var score = 0;
       if (!pass) {
           return score;
       }
       var letters = new Object();
       for (var i=0; i<pass.length; i++) {
           letters[pass[i]] = (letters[pass[i]] || 0) + 1
           score += 5.0 / letters[pass[i]];
       }

       var variations = {
           digits: /\d/.test(pass),
           lower: /[a-z]/.test(pass),
           upper: /[A-Z]/.test(pass),
           nonWords: /\W/.test(pass)
       };

       var variationCount = 0;
       for (var check in variations) {
           variationCount += (variations[check] == true) ? 1 : 0;
       }
       score += (variationCount - 1) * 10;
       if (score > 100) {
           score = 100;
       }
       return parseInt(score);
   }

   return {
       getStrength : function(pass) {
           return getStrength(pass);
       }
   }
});