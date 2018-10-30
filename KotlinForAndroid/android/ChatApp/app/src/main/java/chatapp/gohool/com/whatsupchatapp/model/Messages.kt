package chatapp.gohool.com.whatsupchatapp.model

/**
 * Created by paulodichone on 7/18/17.
 */
class Messages() {

    var message: String? = null
    var send: Boolean? = null
    var time: Long? = null
    var type: String? = null


    constructor(message: String, send: Boolean, time: Long, type: String): this(){
        this.message = message
        this.send = send
        this.time = time
        this.type = type


    }
}