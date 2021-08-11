let mediaUrls = []
let currentUrl = 0;

let restUrl = "https://rassafel-folklore-service.herokuapp.com";

let objectLat;
let objectLon;
let objectTags = [];
let distance = null;

document.addEventListener("DOMContentLoaded", (event) => {
    console.log(window.location.hostname);

    let buildingID = 2;

    fetch(`${restUrl}/folklores/${buildingID}/info`, {
        method: 'GET',
        headers: {
            'Accept': 'application/json'
        }
    })
        .then(response => {
            if(200 <= response.status < 400) {
                return response.json();
            }
            throw new Error(response.json());
        })
        .then(json => {
            let objects = buildObjectInfo(json);
            objects.forEach(obj => document.getElementById("main").append(obj));
        })
        .then(() => {
            loadNearbyObjects();
        })
        .catch((error) => {
            console.log(error);
            notFound();
        });
})

function notFound() {
    console.log("Error");
}


function buildObjectInfo({ id, name, lat, lon, tags, address, imageUrl, labelUrl, urls, description }) {
    objectLat = lat;
    objectLon = lon;
    objectTags = tags;

    // Save media URLs for album
    mediaUrls.push(imageUrl);
    mediaUrls = mediaUrls.concat(urls);


    mediaUrls = sortUrls(mediaUrls);

    let rootDiv = document.createElement("div");
    rootDiv.id = "ObjectInfo";

    let aboutDiv = document.createElement("div");
    aboutDiv.id = "AboutObject";

    let nameDiv = document.createElement("div");
    nameDiv.id = "NameObject";
    nameDiv.innerText = name;
    aboutDiv.append(nameDiv);

    let addressDiv = document.createElement("div");
    addressDiv.id = "AddressObject";
    addressDiv.innerText = address;
    aboutDiv.append(addressDiv);

    let imageDiv = document.createElement("div");
    imageDiv.id = "Image";

    let imageTag = document.createElement("img");
    imageTag.id = "ImageTag";
    imageTag.src = imageUrl;
    imageDiv.append(imageTag)

    let afishaDiv = document.createElement("div");
    afishaDiv.id = "AfishaObject";
    afishaDiv.innerText = "Go to Afisha";
    afishaDiv.addEventListener("click", (event) => {
        console.log(`Go to Afisha building id = ${id}`);
    });
    aboutDiv.append(afishaDiv);

    let descriptionDiv = document.createElement("div");
    descriptionDiv.id = "DesctiptionObject";
    descriptionDiv.innerHTML = description;

    rootDiv.append(imageDiv);
    rootDiv.append(aboutDiv);

    return [rootDiv, descriptionDiv];
}

function sortUrls(urls) {
    let urlsAndTypes = [];
    urls.map((url, index) => {
        fetch(url, {
            method: "HEAD"
        })
            .then(response => response.headers)
            .then(headers => headers.get('content-type'))
            .then(content => urlsAndTypes[index] = { url, content });
    });

    let images = urlsAndTypes.filter(val => val.contentType.startsWith('image/'));
    let videos = urlsAndTypes.filter(val => val.contentType.startsWith('video/'));

    urlsAndTypes = videos.concat(images);

    return urlsAndTypes;
}

function loadNearbyObjects() {
    let rootDiv = document.createElement("div");
    rootDiv.id = "CloseObjects";

    let leftDiv = document.createElement("div");
    leftDiv.style = "padding-left: 15px;";
    leftDiv.innerText = "Рядом";
    rootDiv.append(leftDiv);

    let params = new URLSearchParams();

    // params.set("lat", objectLat);
    // params.set("lon", objectLon);
    // params.set("distance", distance)

    params.set("size", 3);
    params.set("page", 0);

    fetch(`${restUrl}/folklores?${params.toString()}`, { //`${restUrl}/folklores/nearby?${params.toString()}`
        method: 'POST',
        headers: {
            'Accept': 'application/json'
        }
    })
        .then(response => response.json())
        .then(json => {
            json["content"].forEach(element => {
                let object = buildNearbyObject(element);
                rootDiv.append(object);
            });
        })
        .then(() => {
            let rightDiv = document.createElement("div");
            rightDiv.style = "width: 100%;clear: both;";
            rootDiv.append(rightDiv);
            document.getElementById("main").append(rootDiv);
        });
}

function buildNearbyObject({ id, imageUrl, name, address }) {
    let div = document.createElement("div");
    let divImg = document.createElement("div");
    let divName = document.createElement("div");
    let divAddress = document.createElement("div");
    let img = document.createElement("img");

    divImg.className = "CloseObjectIMG";
    divImg.append(img);
    img.src = imageUrl;

    divName.innerText = name;
    divName.className = "CloseObjectName";

    divAddress.innerText = address;
    divAddress.className = "CloseObjectAddress";

    div.className = "CloseObject";
    div.append(divImg);
    div.append(divName);
    div.append(divAddress);

    div.addEventListener("click", (event) => {
        // redirect to object with id
        console.log(`redirect to object with id = ${id}`);
    });

    return div;
}