console.log(`I'm a silly entry point`);

//Pub part
$(document).ready(() => {
    const pubs = ['pub1.png','pub2.png','pub3.png'];
    const randomPub = pubs[Math.floor(Math.random() * 3) +0];
    $('#pub').attr('src','/img/'+randomPub);

    //Paswword home
    $('.passwordChecked').hide();

    $('#passwordCheck').on('click', function(){
        $('.passwordChecked').fadeToggle();
    });

    //Copy to clip board
    window.copyToClipboard = function () {
        var tocopy = document.getElementById('link');
        tocopy.select();
        document.execCommand("copy");
        alert("Votre lien est copié")
    }

})