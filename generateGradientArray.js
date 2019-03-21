//////////////////
//      HTML
//////////////////
//uncomment the below code if you are using a html visual:
/*
var canvas, ctx, ctx2, canvas2;
function updateColor(rgb) {
    var [r, g, b] = [rgb[0], rgb[1], rgb[2]];
    ctx.fillStyle = `rgb(${r}, ${g}, ${b})`;

    ctx.fillRect(0, 0, canvas.width, canvas.height);
}
function updateColor2(rgb) {
    var [r, g, b] = [rgb[0], rgb[1], rgb[2]];
    ctx2.fillStyle = `rgb(${r}, ${g}, ${b})`;

    ctx2.fillRect(0, 0, canvas2.width, canvas2.height);
}

var gradient1 = [
    [255, 255, 0],
        [252, 252, 2],
        [249, 249, 5],
        [247, 247, 7],
        [244, 244, 10],
        [242, 242, 12],
        [239, 239, 15],
        [236, 236, 18],
        [234, 234, 20],
        [231, 231, 23],
        [229, 229, 25],
        [226, 226, 28],
        [224, 224, 30],
        [221, 221, 33],
        [218, 218, 36],
        [216, 216, 38],
        [213, 213, 41],
        [211, 211, 43],
        [208, 208, 46],
        [206, 206, 48],
        [203, 203, 51],
        [200, 200, 54],
        [198, 198, 56],
        [195, 195, 59],
        [193, 193, 61],
        [190, 190, 64],
        [188, 188, 66],
        [185, 185, 69],
        [182, 182, 72],
        [180, 180, 74],
        [177, 177, 77],
        [175, 175, 79],
        [172, 172, 82],
        [170, 170, 85],
        [167, 167, 87],
        [164, 164, 90],
        [162, 162, 92],
        [159, 159, 95],
        [157, 157, 97],
        [154, 154, 100],
        [151, 151, 103],
        [149, 149, 105],
        [146, 146, 108],
        [144, 144, 110],
        [141, 141, 113],
        [139, 139, 115],
        [136, 136, 118],
        [133, 133, 121],
        [131, 131, 123],
        [128, 128, 126],
        [126, 126, 128],
        [123, 123, 131],
        [121, 121, 133],
        [118, 118, 136],
        [115, 115, 139],
        [113, 113, 141],
        [110, 110, 144],
        [108, 108, 146],
        [105, 105, 149],
        [103, 103, 151],
        [100, 100, 154],
        [97, 97, 157],
        [95, 95, 159],
        [92, 92, 162],
        [90, 90, 164],
        [87, 87, 167],
        [84, 84, 170],
        [82, 82, 172],
        [79, 79, 175],
        [77, 77, 177],
        [74, 74, 180],
        [72, 72, 182],
        [69, 69, 185],
        [66, 66, 188],
        [64, 64, 190],
        [61, 61, 193],
        [59, 59, 195],
        [56, 56, 198],
        [54, 54, 200],
        [51, 51, 203],
        [48, 48, 206],
        [46, 46, 208],
        [43, 43, 211],
        [41, 41, 213],
        [38, 38, 216],
        [36, 36, 218],
        [33, 33, 221],
        [30, 30, 224],
        [28, 28, 226],
        [25, 25, 229],
        [23, 23, 231],
        [20, 20, 234],
        [18, 18, 236],
        [15, 15, 239],
        [12, 12, 242],
        [10, 10, 244],
        [7,7, 247],
        [5,5, 249],
        [2,2, 252],
        [0,0, 255]
];

var [x,y] = [0, 0];
var reverseRGB = false;
const interval = 2.0; //(seconds)

window.onload = function() {
    canvas = document.getElementById("canvas");
    ctx = canvas.getContext("2d");
    canvas2 = document.getElementById("canvas2");
    ctx2 = canvas2.getContext("2d");

    setInterval(function(){
        if(x == gradient1.length - 1) reverseRGB = true;
        else if(x == 0) reverseRGB = false;
    
        updateColor2(gradient1[x]);
    
        if(!reverseRGB) x++;
        else if(reverseRGB) x--;
    }, (interval/gradient1.length * 1000));
    setInterval(function(){
        updateColor(array[y]);
        
        console.log(y);
        if(y + 1 == array.length) y = 0;
        else y++;
    }, interval/gradient1.length * 1000);
}
*/

/////////////////
////   SCRIPT
/////////////////

//call the function generateGradientArray() and it will return a 
//generated gradient array


const iterations = {
    total:100,      //iterations in a cycle from start to mid
    ratioSlow:80    //the percent of cycles to go slow
};   
const color = {start:[255, 255, 0], mid:[0, 0, 0], end:[0, 0, 255]};    //I know that i don't actually use mid, but i included it in the event that i do end up modifying this code to be more versatile
const discriminator = 3;    //this further modifies the ratioSlow, slowing the colors down by a factor of that number.
//set the discriminator to 1 to remove its effects. set it to a number < 1 && > 0 to reverse its effects (a way of bypassing the ratioSlow)


var newcolor;
var array = [];

function generateGradientArray() {
    array = [];
    var newcolor = [];
    var base = 255.0/(iterations.total/2);

    for(var i=0; i <= (iterations.total/2); i++) {
        newcolor = [];
        if(i > (iterations.total/2) * (iterations.ratioSlow/100)) {
            base = array[((iterations.total/2) * (iterations.ratioSlow/100)) - 1][0]/((iterations.total/2) * (1 - (iterations.ratioSlow/100)));
            //console.log((iterations.total/2) * (iterations.ratioSlow/100));
            //console.log((1 - (iterations.ratioSlow/100)));
            //console.log(((iterations.total/2) * (1 - (iterations.ratioSlow/100))));
            newcolor.push(Math.round(array[((iterations.total/2) * (iterations.ratioSlow/100)) - 1][0] - (base * (i - (iterations.total/2) * (iterations.ratioSlow/100)))));
            newcolor.push(Math.round(array[((iterations.total/2) * (iterations.ratioSlow/100)) - 1][0] - (base * (i - (iterations.total/2) * (iterations.ratioSlow/100)))));
            newcolor.push(0);
            //console.log(Math.round(array[((iterations.total/2) * (iterations.ratioSlow/100)) - 1][0] - (base * (i - (iterations.total/2) * (iterations.ratioSlow/100)))));  
        } else {
            newcolor.push(Math.round(color.start[0] - (base * i)/discriminator));
            newcolor.push(Math.round(color.start[1] - (base * i)/discriminator));
            newcolor.push(0);
            //console.log(Math.round(color.start[0] - (base * i)/discriminator));
        }
        array.push(newcolor);
    }  
    //so, i can essentially take the values that i just got, because we go
    //slowly thru blue, then quickly thru white. if i just reverse the values,
    //we'll be going quickly thru white and slowly thru yellow until yellow is 
    //going at full blast.
    
    for(var i=(array.length-1); i!=0; i--) { 
        //we subract 1 from array.length so that we ignore the final value of the
        //array (0, 0, 0), so that our final array ends up being the proper length
        newcolor = [];
        newcolor.push(color.end[0], color.end[1], array[i-1][0]);
        //console.log(array[i-1][0]);
        array.push(newcolor);
    }

    //okay, so at this point we have an array that we can thru that will:
    //start at yellow, go to white, then to blue.
    //if we were to repeat this array, we would instantly jump to yellow
    //instead of reversing the process (going from blue to white to yellow)

    //so, how can we reverse the process in order to create a totally smooth
    //transition? simple. we use the JS array.reverse() mehtod.
    //this little tool will completely reverse our array from start to finish.
    //in order to combine the original array with the reversed one, i'm going to
    //push() the reverse() function. watch...

    //array.push(array.reverse());

    // ^ this does not work. know why? the reverse() function doesn't create a 
    //temporary copy of the array... it mutiliate the actual array ITSELF.
    //even if i were to create a temporary variable and assign it to array.reverse(),
    //the array itself would still get reverse. that's just how JS is set up;
    //nothing i can do to change that.

    //we can bypass this restriction using .concat() (that's a function extremely
    //similar to .push()) and a sliced potion of our array. like so:

    array = array.concat(array.slice().reverse());

    //what we do is take a chunk of our target array using the slice() method.
    //by not passing a number into the .slice() parameters, we are actually slicing
    //the array as a whole. this is the temporary array that we want.
    //next, we reverse that temporary array using the .reverse() function,
    //and finally we .concat() it. there's a subtle difference between .concat()
    //and .push(), the main thing is that concat() doesn't actually change an array,
    //it only combines two into a new one. however, we nullify that aspect of concat() by 
    //setting the concatted arrays equal to the targeted array.
    
    return array;
}



    