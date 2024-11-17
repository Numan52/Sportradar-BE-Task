addEventListener("DOMContentLoaded", async function(){
    const filterSportSelect = document.getElementById("filterSport");
    const addEventSportSelect = document.getElementById("select-event-sport");
    const teamACitySelect = document.getElementById("teamA-city-select");
    const teamBCitySelect = document.getElementById("teamB-city-select");
    const venueCitySelect = document.getElementById("venue-city-select");
    const venueCountrySelect = document.getElementById("venue-country-select");

    setupSubmitEventHandler()
    await displayEvents()
    await populateDropdown("http://localhost:8080/api/sports", [filterSportSelect, addEventSportSelect])
    await populateDropdown("http://localhost:8080/api/utility/cities", [teamACitySelect, teamBCitySelect, venueCitySelect])
    await populateDropdown("http://localhost:8080/api/utility/countries", [venueCountrySelect])

    addOtherSelectedListener()
})


async function displayEvents() {
    
    const eventsDiv = document.getElementsByClassName("events").item(0)
    const noEventsMessage = document.getElementsByClassName("no-events-message").item(0);

    eventsDiv.innerHTML = ""

    try {
        const eventsData = await fetchEventsData()

        if (eventsData.length === 0) {
            noEventsMessage.style.display = "block"
        } else {
            noEventsMessage.style.display = "none"
            eventsData.forEach(event => {
                createEventDiv(event, eventsDiv)
            });
        }
        
    } catch (error) {
        console.error("Error in displayEvents:", error);

        noEventsMessage.style.display = "block";
    }
    
}


async function fetchEventsData() {
    const response = await fetch("http://localhost:8080/api/events/getEvents", {
        method: "GET",
        headers: { 
            "Content-Type": "application/json",
        }
    })
    if(response.ok) {
        const data = await response.json()
        console.log(data)
        return data
    }

    
}


function createEventDiv(event, eventsDiv) {
    const eventDiv = document.createElement("div")
    eventDiv.className = "event"
    
    const headlineP = document.createElement("h2")
    headlineP.textContent = event.description

    const dateP = document.createElement("p")
    dateP.innerHTML = `<strong>Date:</strong> ${event.date}`

    const timeP = document.createElement("p")
    timeP.innerHTML = `<strong>Time:</strong> ${event.time}`
    
    const sportP = document.createElement("p")
    sportP.innerHTML = `<strong>Sport:</strong> ${event.sport}`

    const participantsP = document.createElement("p")
    participantsP.innerHTML = `<strong>Participants:</strong> ${event.teams[0].teamName} vs ${event.teams[1].teamName}`

    const venueNameP = document.createElement("p")
    venueNameP.innerHTML = `<strong>Venue:</strong> ${event.venue.name}`

    const venueLocationP = document.createElement("p")
    venueLocationP.innerHTML = `<strong>Location:</strong> ${event.venue.city}, ${event.venue.country}`

    const venueCapacityP = document.createElement("p")
    venueCapacityP.innerHTML = `<strong>Capacity:</strong> ${event.venue.capacity}`

    eventDiv.appendChild(headlineP)
    eventDiv.appendChild(dateP)
    eventDiv.appendChild(timeP)
    eventDiv.appendChild(sportP)
    eventDiv.appendChild(participantsP)
    eventDiv.appendChild(venueNameP)
    eventDiv.appendChild(venueLocationP)
    eventDiv.appendChild(venueCapacityP)
    eventsDiv.appendChild(eventDiv) 

}


function setupSubmitEventHandler() {
    document.getElementById("eventForm").addEventListener("submit", async function(event) {
        event.preventDefault();
        const eventDescription = document.getElementById("eventDescription").value;
        const date = document.getElementById("eventDate").value;
        const time = document.getElementById("eventTime").value;
        const entranceFee = document.getElementById("entranceFee").value;

        const sportSelection = document.getElementById("select-event-sport").value;
        const sportInput = document.getElementById("event-sport-input").value;

        const teamA = document.getElementById("teamA").value;
        const teamACitySelection = document.getElementById("teamA-city-select").value;
        const teamACityInput = document.getElementById("teamA-city-input").value;
        const foundingDateA = document.getElementById("foundingDateA").value;

        const teamB = document.getElementById("teamB").value;
        const teamBCitySelection = document.getElementById("teamB-city-select").value;
        const teamBCityInput = document.getElementById("teamB-city-input").value;
        const foundingDateB = document.getElementById("foundingDateB").value;

        const venueName = document.getElementById("venueName").value;
        const VenueCitySelection = document.getElementById("venue-city-select").value;
        const venueCityInput = document.getElementById("venue-city-input").value;
        const venueCountrySelection = document.getElementById("venue-country-select").value;
        const venueCountryInput = document.getElementById("venue-country-input").value;
        const venueAddress = document.getElementById("venueAddress").value;
        const venueCapacity = document.getElementById("venueCapacity").value;
        
        const sport = sportSelection === "Other" ? sportInput : sportSelection
        const teamACity = teamACitySelection === "Other" ? teamACityInput : teamACitySelection
        const teamBCity = teamBCitySelection === "Other" ? teamBCityInput : teamBCitySelection
        const venueCity = VenueCitySelection === "Other" ? venueCityInput : VenueCitySelection
        const venueCountry = venueCountrySelection === "Other" ? venueCountryInput : venueCountrySelection

        if (eventDescription && date && time && sport && entranceFee && sport && teamA && teamACity
            && teamB && teamBCity && venueCity && venueCountry){  
            const eventDto = {
                date,
                time,
                description: eventDescription,
                entranceFee,
                sport,
                venue: {
                    name: venueName,
                    address: venueAddress,
                    city: venueCity,
                    country: venueCountry,
                    capacity: venueCapacity
                },
                teams: [
                    {
                        teamName: teamA,
                        city: teamACity,
                        foundingYear: foundingDateA
                    },
                    {
                        teamName: teamB,
                        city: teamBCity,
                        foundingYear: foundingDateB
                    }
                ]
            }

            await sendEventDto(eventDto)
            
            document.getElementById("eventForm").reset();
        } else {
            const errorDiv = document.getElementsByClassName("add-event-error-message").item(0)
            errorDiv.textContent = "Please fill out every mandatory field. (marked with *)"
        }
    });
}


function displayNoEventsFoundMsg() {

}


async function sendEventDto(event) {
    try {
        const response = await fetch("http://localhost:8080/api/events/addEvent", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(event)
        });
        
        if (response.ok) {
            console.log("Event successfully added!");
            await displayEvents()
        } else {
            console.error("Failed to add event.");
            const errorDiv = document.getElementsByClassName("add-event-error-message").item(0)
            errorDiv.textContent = "An error occurred while adding a new event. Please try again."
            
            window.scrollTo({
                top: document.body.scrollHeight,
                behavior: "smooth"
            });
        }
    } catch (error) {
        console.error("Error:", error);
    }
}


async function filterEvents() {
    const eventsDiv = document.getElementsByClassName("events").item(0);
    const noEventsMessage = document.getElementsByClassName("no-events-message").item(0);

    const sport = document.getElementById("filterSport").value;
    const date = document.getElementById("filterDate").value;

    const queryParams = new URLSearchParams();
    if (sport) queryParams.append("sport", sport);
    if (date) queryParams.append("date", date);

    const response = await fetch(`http://localhost:8080/api/events/filter?${queryParams.toString()}`);
    if (response.ok) {
        const eventsData = await response.json();
        eventsDiv.innerHTML = "";
        
        if(eventsData.length === 0) {
            noEventsMessage.style.display = "block"
        } else {
            noEventsMessage.style.display = "none"
            eventsData.forEach(event => createEventDiv(event, eventsDiv));
        }
        
    }


}


async function populateDropdown(apiUrl, elementsArray) {
    try {
        const response = await fetch(apiUrl); 
        if (response.ok) {
            const data = await response.json();
            
            elementsArray.forEach(selectElement => {
                data.forEach(dataElement => {
                    const option = document.createElement("option");
                    option.value = dataElement;
                    option.textContent = dataElement;
                    selectElement.appendChild(option);
                });
                const otherOption = document.createElement("option")
                otherOption.value = "Other";
                otherOption.textContent = "Other";
                selectElement.appendChild(otherOption)
            })
            
        } else {
            console.error("Error fetching sports data:", response.statusText);
        }
    } catch (error) {
        console.error("Error fetching sports data:", error);
    }
}


function addOtherSelectedListener() { 
    const selectElements = document.querySelectorAll("select[data-other-input]")

    selectElements.forEach(selectElement => {
        selectElement.addEventListener("change", function() {
            const inputElementId = this.getAttribute("data-other-input")
            const inputElement = document.getElementById(inputElementId)

            if (selectElement.value == "Other") {
                inputElement.style.display = "block"
            } else {
                inputElement.style.display = "none"
            }

        })
    })
}









