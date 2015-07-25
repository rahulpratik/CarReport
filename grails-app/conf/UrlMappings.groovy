class UrlMappings {

	static mappings = {

        "/api/carReports"(resources: "car")
        "/api/accident/minor"(controller: 'car', action: 'minor')
        "/api/accident/serious"(controller: 'car', action: 'serious')
        "/api/accident/totalled"(controller: 'car', action: 'totalled')

        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }
        "/"(view:"/index")
        "500"(controller: 'error', action: "handleException")
	}
}
