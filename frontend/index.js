addEventListener("load", async function(){
    setupSubmitEventHandler()
    await displayEvents()
    await populateSportDropdown()
})

async function displayEvents() {
    const eventsData = await fetchEventsData()
    
    const eventsDiv = document.getElementsByClassName("events").item(0)
    eventsDiv.innerHTML = ""
    
    eventsData.forEach(event => {
        createEventDiv(event, eventsDiv)
    });
    
    
}


async function fetchEventsData() {
    try {
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
    } catch (error) {
        console.error("Error while fetching event data: ", error)
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
        const sport = document.getElementById("eventSport").value;
        const entranceFee = document.getElementById("entranceFee").value;
        const teamA = document.getElementById("teamA").value;
        const teamCityA = document.getElementById("teamCityA").value;
        const foundingDateA = document.getElementById("foundingDateA").value;
        const teamB = document.getElementById("teamB").value;
        const teamCityB = document.getElementById("teamCityB").value;
        const foundingDateB = document.getElementById("foundingDateB").value;
        const venueName = document.getElementById("venueName").value;
        const venueCity = document.getElementById("venueCity").value;
        const venueCountry = document.getElementById("venueCountry").value;
        const venueAddress = document.getElementById("venueAddress").value;
        const venueCapacity = document.getElementById("venueCapacity").value;
        
        if (eventDescription && date && time && sport && entranceFee && sport && teamA && teamCityA
            && teamB && teamCityB ){  
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
                        city: teamCityA,
                        foundingYear: foundingDateA
                    },
                    {
                        teamName: teamB,
                        city: teamCityB,
                        foundingYear: foundingDateB
                    }
                ]
            }

            await sendEventDto(eventDto)
            
            document.getElementById("eventForm").reset();
        }
    });
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
    const sport = document.getElementById("filterSport").value;
    const date = document.getElementById("filterDate").value;

    const queryParams = new URLSearchParams();
    if (sport) queryParams.append("sport", sport);
    if (date) queryParams.append("date", date);

    const response = await fetch(`http://localhost:8080/api/events/filter?${queryParams.toString()}`);
    if (response.ok) {
        const eventsData = await response.json();
        const eventsDiv = document.getElementsByClassName("events").item(0);
        eventsDiv.innerHTML = "";
        eventsData.forEach(event => createEventDiv(event, eventsDiv));
    }


}


async function populateSportDropdown() {
    try {
        const response = await fetch("http://localhost:8080/api/sports"); // Adjust the endpoint to your API
        if (response.ok) {
            const sports = await response.json();
            const sportSelect = document.getElementById("filterSport");
            
            sports.forEach(sport => {
                const option = document.createElement("option");
                option.value = sport.name;
                option.textContent = sport.name;
                sportSelect.appendChild(option);
            });
        }
    } catch (error) {
        console.error("Error fetching sports data:", error);
    }
}







