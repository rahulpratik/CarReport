class UrlMappings {

	static mappings = {

        "/api/carReports"(resources: "car")
       

        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }
        "/"(view:"/index")
        "500"(controller: 'error', action: "handleException")
	}
}
