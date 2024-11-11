addEventListener("load", async function(){
    await displayEvents()
    setupSubmitEventHandler()
})

async function displayEvents() {
    const eventsData = await fetchEventsData()
    const eventsDiv = document.getElementsByClassName("events")
    
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

function setupSubmitEventHandler() {
    document.getElementById("eventForm").addEventListener("submit", function(event) {
        event.preventDefault();
        const date = document.getElementById("eventDate").value;
        const time = document.getElementById("eventTime").value;
        const sport = document.getElementById("eventSport").value;
        const teams = document.getElementById("eventTeams").value;
        
        if (date && time && sport && teams) {
            const eventContainer = document.createElement("div");
            eventContainer.className = "event";
            eventContainer.innerHTML = `
                <h2>${sport}: ${teams}</h2>
                <p><strong>Date:</strong> ${date}</p>
                <p><strong>Time:</strong> ${time}</p>
                <p><strong>Sport:</strong> ${sport}</p>
                <p><strong>Participants:</strong> ${teams}</p>
            `;
            document.querySelector(".container").appendChild(eventContainer);
            document.getElementById("eventForm").reset();
        }
    });
}







