/*
* @Author: love
* @Date:   2017-09-25 14:17:11
* @Last Modified by:   love
* @Last Modified time: 2017-09-25 15:53:33
*/
var count=0;
var carts=[
			'/img/slider.jpg',
			'/img/slider1.jpg',
			'/img/slider2.jpg',
			'/img/slider3.jpg',
			'/img/slider4.jpg',
			];
function changeImage(){
	if(count==5){
		count=0;

	};
	document.getElementById('banner').src=carts[count];
	count++;
}
var timer=setInterval(changeImage, 2000);
function starPic(num){
	clearInterval(timer);
	var index=num;
	document.getElementById('banner').src=carts[index-1];
	count=index;

}
function stopPic(){
	timer=setInterval(changeImage, 2000);
}

