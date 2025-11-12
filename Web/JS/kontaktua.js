document.getElementById('kontaktuaForm').addEventListener('submit', function(e) {
    e.preventDefault();
    
    const izena = document.getElementById('izena').value;
    const email = document.getElementById('email').value;

    alert('Eskerrik asko ' + izena + '!\n\nZure mezua bidali da arrakastaz.\n\nLaster kontaktatuko zaitugu ' + email + ' helbidean.');

    document.getElementById('kontaktuaForm').reset();
});