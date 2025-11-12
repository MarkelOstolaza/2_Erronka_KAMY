const canvas = document.getElementById('canvas');
const ctx = canvas.getContext('2d');

ctx.fillStyle = '#FFD700';
ctx.beginPath();
ctx.arc(150, 80, 40, 0, Math.PI * 2);
ctx.fill();

ctx.fillStyle = '#8B4513';
ctx.fillRect(130, 120, 40, 100);

ctx.fillStyle = '#228B22';
ctx.beginPath();
ctx.moveTo(150, 60);
ctx.lineTo(100, 140);
ctx.lineTo(200, 140);
ctx.closePath();
ctx.fill();

ctx.fillStyle = '#FF6347';
ctx.beginPath();
ctx.arc(135, 210, 8, 0, Math.PI * 2);
ctx.fill();
ctx.beginPath();
ctx.arc(165, 210, 8, 0, Math.PI * 2);
ctx.fill();

ctx.fillStyle = '#FFB6C1';
ctx.fillRect(140, 230, 20, 50);
ctx.fillRect(190, 230, 20, 50);