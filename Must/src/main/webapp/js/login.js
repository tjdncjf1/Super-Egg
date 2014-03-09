var db;

function init() {
	document.addEventListener("deviceready", onDeviceReady, false);
	db = window.openDatabase("MDB", "1.0", "Must_DB", 1000000);
}

function onDeviceReady() {
	db.transaction(setupTable, dbErrorHandler, getEntries);
}

function setupTable(tx) {
	tx.executeSql('CREATE TABLE IF NOT EXISTS LoginUser (no INTEGER PRIMARY KEY AUTOINCREMENT, Email TEXT NOT NULL, Password TEXT NOT NULL)');
}

function dbErrorHandler(err) {
	alert("Error processing SQL: " + err.code);
}

function getEntries() {
	db.transaction(queryDB, dbErrorHandler);
}

function queryDB(tx) {
	tx.executeSql('SELECT * FROM LoginUser',[],renderList,dbErrorHandler);
}

