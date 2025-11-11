function pagatu() {
    const konfirmatu = confirm('Erosketa konfirmatzea nahi duzu?');
    
    if (konfirmatu) {
        const kodigos = Math.random().toString(36).substring(2, 10).toUpperCase();
        alert('Erosketa konfirmatu da!\n\nEntraden kodiga: ' + kodigos + '\n\nEskerrik asko!');
        window.location.href = 'weborria.html';
    }
}