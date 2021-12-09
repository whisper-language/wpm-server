#[macro_use] extern crate rocket;


#[get("/")]
fn index() -> &'static str {
    "wpm-server"
}

#[get("/version")]              // <- route attribute
fn version() -> &'static str {  // <- request handler
    "0.0.1"
}


#[derive(Responder)]
#[response(status = 418, content_type = "json")]
struct RawTeapotJson(&'static str);

#[get("/search/<words>")]
fn search(words: String) -> RawTeapotJson {
    RawTeapotJson("[{},{}]")
}

#[launch]
fn rocket() -> _ {
    rocket::build().mount("/", routes![index,search])
}