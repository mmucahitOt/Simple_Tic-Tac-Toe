package tictactoe

var gameOn = true


var boardValues = mutableListOf<MutableList<String>>(
    //Two dimentional list containing the values of cells on the board. "_": Empty / "O": O piece / "X": X piece.
    mutableListOf<String>("_","_","_"),
    mutableListOf<String>("_","_","_"),
    mutableListOf<String>("_","_","_")
)


fun board() {
    //Generate the board by putting @board values in it.
    println("---------")
    println("| " +boardValues[0][0] + " " +boardValues[0][1] + " " + boardValues[0][2] + " |")
    println("| " +boardValues[1][0] + " " +boardValues[1][1] + " " + boardValues[1][2] + " |")
    println("| " +boardValues[2][0] + " " +boardValues[2][1] + " " + boardValues[2][2] + " |")
    println("---------")
}

fun convertInputToPosition(input: String): List<Int> {
    // Convert the input to a list containing position indices.
    return input.split(" ").map {it.toInt() - 1}
}

fun getMoveAndCheckIt(): List<Int> {
    //Check the input user entered if it is valid or not. If it is valid return a list containing its position
    println("Enter the coordinates:")
    while (true) {
        var inputFromPlayer = readLine()!!
        var inputFromPlayerList = inputFromPlayer.split(" ")
        val numbersStringType = listOf<String>("1","2","3","4","5","6","7","8","9","0")
        val validNumbers = listOf<String>("1","2","3")
        if (!numbersStringType.contains(inputFromPlayerList[0]) || !numbersStringType.contains(inputFromPlayerList[1])) {
            println("You should enter numbers!")
            println("Enter the coordinates:")
            continue
        }
        if (!validNumbers.contains(inputFromPlayerList[0]) || !validNumbers.contains(inputFromPlayerList[1])) {
            println("Coordinates should be from 1 to 3!")
            println("Enter the coordinates:")
            continue
        }
        var (y, x) = convertInputToPosition(input = inputFromPlayer)

        if (boardValues[y][x] != "_") {
            println("This cell is occupied! Choose another one!")
            println("Enter the coordinates:")
            continue
        }
        return convertInputToPosition(input = inputFromPlayer)
    }
}

fun movePieceToItsPosition(movePosition: List<Int>,currentPiece: String) {
    var (y, x) = movePosition
    boardValues[y][x] = currentPiece
}


fun getPreviousMoves() {
    //Ask user to enter previous moves.
    println("Enter cells:")
    val input = readLine()!!
    for (i in 0..9) {
        boardValues[0][0] = input[0].toString()
        boardValues[0][1] = input[1].toString()
        boardValues[0][2] = input[2].toString()
        boardValues[1][0] = input[3].toString()
        boardValues[1][1] = input[4].toString()
        boardValues[1][2] = input[5].toString()
        boardValues[2][0] = input[6].toString()
        boardValues[2][1] = input[7].toString()
        boardValues[2][2] = input[8].toString()
    }
}

fun checkWin(piece:String) {
    // Check possible win situations and prevent generating more than one WIN LINE such as(OOO).
    val horizLine1 = (boardValues[0][0] == piece && boardValues[0][1] == piece && boardValues[0][2] == piece)
    val horizLine2 =(boardValues[1][0] == piece && boardValues[1][1] == piece && boardValues[1][2] == piece)
    val horizLine3 = (boardValues[2][0] == piece && boardValues[2][1] == piece && boardValues[2][2] == piece)
    val verticalLine1 = (boardValues[0][0] == piece && boardValues[1][0] == piece && boardValues[2][0] == piece)
    val verticalLine2 = (boardValues[0][1] == piece && boardValues[1][1] == piece && boardValues[2][1] == piece)
    val verticalLine3 = (boardValues[0][2] == piece && boardValues[1][2] == piece && boardValues[2][2] == piece)
    val diagonalLine1 = (boardValues[0][0] == piece && boardValues[1][1] == piece && boardValues[2][2] == piece)
    val diagonalLine2 = (boardValues[2][0] == piece && boardValues[1][1] == piece && boardValues[0][2] == piece)

    var piece_ = ""

    if (piece == "X") {
        piece_ = "O"
    } else if (piece == "O") {
        piece_ = "X"
    }
    val horizLine1_ = (boardValues[0][0] == piece_ && boardValues[0][1] == piece_ && boardValues[0][2] == piece_)
    val horizLine2_ =(boardValues[1][0] == piece_ && boardValues[1][1] == piece_ && boardValues[1][2] == piece_)
    val horizLine3_ = (boardValues[2][0] == piece_ && boardValues[2][1] == piece_ && boardValues[2][2] == piece_)
    val verticalLine1_ = (boardValues[0][0] == piece_ && boardValues[1][0] == piece_ && boardValues[2][0] == piece_)
    val verticalLine2_ = (boardValues[0][1] == piece_ && boardValues[1][1] == piece_ && boardValues[2][1] == piece_)
    val verticalLine3_ = (boardValues[0][2] == piece_ && boardValues[1][2] == piece_ && boardValues[2][2] == piece_)
    val diagonalLine1_ = (boardValues[0][0] == piece_ && boardValues[1][1] == piece_ && boardValues[2][2] == piece_)
    val diagonalLine2_ = (boardValues[2][0] == piece_ && boardValues[1][1] == piece_ && boardValues[0][2] == piece_)
    var countsOfWinsForPiece = 0
    var countsOfWinsForPiece_ = 0

    for (i in mutableListOf<Boolean>(horizLine1, horizLine2, horizLine3, verticalLine1, verticalLine2, verticalLine3, diagonalLine1, diagonalLine2)){
        if (i == true) ++countsOfWinsForPiece
    }

    for (i in mutableListOf<Boolean>(horizLine1_, horizLine2_, horizLine3_, verticalLine1_, verticalLine2_, verticalLine3_, diagonalLine1_, diagonalLine2_)){
        if (i == true) ++countsOfWinsForPiece_
    }

    if (countsOfWinsForPiece > 0 || countsOfWinsForPiece_ > 0) {
        println("$piece wins")
        gameOn = false
    }
}


fun checkDraw() {

    var countsEmptyCell = 0
    for (y in 0..2) {
        for (x in 0..2) {
            if (boardValues[y][x] == "_") {
                ++countsEmptyCell
            }
        }
    }
    if (countsEmptyCell == 0) {
        println("Draw")
        gameOn = false
    }
}

fun impossibleCountsOfPieces() {
    var countsXpieces = 0
    var countsOpieces = 0
    for (y in 0..2) {
        for (x in 0..2) {
            if (boardValues[y][x] == "X") ++countsXpieces
            if (boardValues[y][x] == "O") ++countsOpieces
        }
    }
    if (countsXpieces == countsOpieces || countsXpieces-1 == countsOpieces || countsXpieces == countsOpieces -1) {
    }else{
        println("Impossible")
        gameOn = false
    }
}


fun main() {
    var gameProgression = 0
    var currentPiece = ""
    val pieceX = "X"
    val pieceO = "O"

    board()

    while (gameOn){
        if (gameProgression % 2 == 0) currentPiece = pieceX
        if (gameProgression % 2 == 1) currentPiece = pieceO
        checkWin(piece = "X")
        if (!gameOn) return
        checkWin(piece = "O")
        if (!gameOn) return
        checkDraw()
        if (!gameOn) return
        impossibleCountsOfPieces()
        if (!gameOn) return
        movePieceToItsPosition(movePosition = getMoveAndCheckIt(), currentPiece = currentPiece)
        board()

        ++gameProgression
    }
}