export class GameChangeRequest {

    constructor(player, commandString, commandDetails) {
        this.player = player
        this.command = commandString
        this.commandDetails = commandDetails
    }
}
