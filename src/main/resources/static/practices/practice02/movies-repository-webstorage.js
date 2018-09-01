<!-- this is not used -->

var repository = [];
var $$ = (id) => document.getElementById(id);
var $$value = (id) => $$(id).value;
var $$selected = (id) => $$(id).selected;
var $$checkedValue = (id1,id2) => $$(id1).checked? $$(id1).value : $$(id2).checked? $$(id2).value: "";
var $$checkedValue = (ids) => {
    var checkeds = ids.filter((_id) => $$(_id).checked);
    return checkeds.length == 1?$$(checkeds[0]).value:"";
};

var movieFromForm = () =>{
    var id = $$value("movie.field.id");
    var title = $$value("movie.field.title");
    var releasedDate = $$value("movie.field.releasedDate");
    var budget = $$value("movie.field.budget");
    var poster = $$value("movie.field.poster");
    var rating = $$value("movie.field.rating");
    var category = $$value("movie.field.category");
    category = document.getElementById("movie.field.category").value;
    var result = $$checkedValue(["movie.field.result.winner","movie.field.result.nominee"]);
    result = $$("movie.field.result.winner").value == result;
    return {id: id, title: title, releasedDate: releasedDate, budget: budget, poster: poster, rating: rating, category: category, result: result};
};

var clearForm = () =>{
    $$("movie.field.id").value=null;
    $$("movie.field.title").value=null;
    $$("movie.field.releasedDate").value=null;
    $$("movie.field.budget").value=null;
    $$("movie.field.poster").value=null;
    $$("movie.field.rating").value=null;
    $$("movie.field.category").value=null;
    $$("movie.field.result.nominee").checked=null;
    $$("movie.field.result.winner").checked=null;
};

var addRowToTable = (movie) => {
    var row = "";
    row += "<tr>";
    row += "<td>"+movie.title+"</td>";
    row += "<td>"+movie.releasedDate+"</td>";
    row += "<td>"+(movie.budget || "")+"</td>";
    row += "<td><img";
    row += "	src=\""+(movie.poster || "")+"\" ";
    row += "	width=\""+"30px"+"\"></td>";
    row += "<td>"+(movie.rating || "")+"</td>";
    row += "<td>"+(movie.category || "")+"</td>";
    row += "<td>"+(movie.result)+"</td>";
    row += "<td><button id=\"btnDelete"+movie.id+"\" data-movie-id=\""+movie.id+"\">"+"delete"+"</button></td>";
    row += "</tr>";
    return row;
};
var fieldValueIsRequired = (field, help, label, value) => {
    var result = true;
    if (!value){
        $$(field).style.borderColor = "red";
        $$(help).style.color = "red";
        $$(help).innerHTML = label+" is required";
        result = false;
    } else {
        $$(field).style.borderColor = "";
        $$(help).style.color = "";
        $$(help).innerHTML = "";
    }
    return result;
};
var movieIsValid = (movie) => {
    var result = true;
    result &= fieldValueIsRequired("movie.field.title","movie.help.title","title", movie.title);
    result &= fieldValueIsRequired("movie.field.releasedDate","movie.help.releasedDate","released date", movie.releasedDate);
    return result;
};
var saveMovieLs = (movie) => {
    if (!movie.id){
        movie.id = new Date().getTime();
    }
    repository.push(movie);
    localStorage.setItem("movies",JSON.stringify(repository));
};


var onclickBtnSave = (e) => {
    var movie = movieFromForm();
    if (movieIsValid(movie)){
        saveMovieLs(movie);
        clearForm();
        loadAndShowMovies();
    }
};
var onclickBtnClear = (e) => clearForm();

var deleteMovieLs = (id) => {
    var index = repository.findIndex(_m => _m.id == id);
    repository.splice(index,1);
    localStorage.setItem("movies",JSON.stringify(repository));
};
var deleteMovie = (id) => {
    deleteMovieLs(id);
};
var onclickBtnDelete = (e) => {
    deleteMovie(e.target.dataset.movieId);
    loadAndShowMovies();
};
var addRows = () => {
    repository.forEach(_movie => {
        $$("tblBody").innerHTML += (addRowToTable(_movie));
});
};
var registerRowEvents = () => {
    repository.forEach(_movie => {
        $$("btnDelete"+_movie.id).onclick = onclickBtnDelete;
});
};
var showMovies = () => {
    $$("tblBody").innerHTML = "";
    $$("tblcaption").innerText = ""+repository.length+" movies";
    addRows();
    registerRowEvents();
}
var loadMoviesLs = () => {
    repository = JSON.parse(localStorage.getItem("movies")) || [];
};
// var loadMovies = () => {
//     getMovies();
// };
var loadAndShowMovies = () => {
    loadMoviesLs();
    showMovies();
};
init = () => {
    //localStorage.removeItem("movies");
    var title = "html 5";
    document.getElementsByTagName("title").item(0).innerText = title;
    document.getElementsByClassName("pagetitle").item(0).innerText = title;
    $$("btnSave").onclick = onclickBtnSave;
    $$("btnClear").onclick = onclickBtnClear;
    $$("chbOrientation").onclick = (e) => {
        if ($$("secOrientation").style.display == "none"){
            $$("secOrientation").style.display = "";
        } else {
            $$("secOrientation").style.display = "none";
        }
    };
    loadAndShowMovies();
};

//
Array.prototype.add = function(elem) {
    this.push(elem);
};

var test = [];
test.add(1).add(2);

window.onload = init;