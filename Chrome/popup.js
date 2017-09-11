chrome.runtime.onMessage.addListener(function(request, sender) {
	if (request.action == "getSource") {

		var list = parseHTML(request.source);
		message.innerText = list;
		var data = JSON.stringify({
			"mobileNumbers" :  list 
		});
		console.log(data)
		var url = "http://localhost:8080/DBService/dbservice/insertRow";
		var xhr = new XMLHttpRequest();
		xhr.open("POST", url, true);
		xhr.setRequestHeader('Content-type', 'application/json');

		
		xhr.send(data);

	}
});

function onWindowLoad() {

	var message = document.querySelector('#message');

	chrome.tabs.executeScript(null, {
		file : "myscript.js"
	}, function() {
		// If you try and inject into an extensions page or the webstore/NTP
		// you'll
		// get an error
		if (chrome.runtime.lastError) {
			message.innerText = 'There was an error injecting script : \n'
					+ chrome.runtime.lastError.message;
		}
	});

}

function parseHTML(text) {
	var numbers = "";
	var pattern = /[0-9]{10}/i;

	var coordinates = text.split("\n");

	for (var i = 0; i < coordinates.length; i++) {

		line = coordinates[i];
		var count = 0;
		while (pattern.test(line)) {

			numbers += line.substring(line.search(pattern), line
					.search(pattern) + 10);
			line = line.replace(pattern, "");
			numbers += ",";
			count = count + 1;
			if (pattern.test(line))
				break;
		}

	}

	return numbers;
}

window.onload = onWindowLoad;