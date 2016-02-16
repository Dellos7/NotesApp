var app = angular.module( "app", [] );

app.controller( "MyController", [ "$scope", "$http", function( $scope, $http ) {
    $scope.filter = "";
    $scope.data = [];
    $scope.newTitle = "";
    $scope.newText = "";

    $scope.retrieveNotes = function() {
        $http.get( "http://localhost:8080/rest/notes" )
            .then( function(res) {
                //console.log(res);
                if( Object.prototype.toString.call( res.data.notes.note ) === '[object Array]' ) {
                    $scope.data = res.data.notes.note;
                }
                else {
                    $scope.data.push( res.data.notes.note );
                }
            }, function(res) {
                $scope.data = [ { id: -1, title: "Error!", text: res.status } ];
        });
    }

    $scope.addNote = function() {
        if( $scope.newTitle != "" ) {
            var note = { 'note': { title: $scope.newTitle, text: $scope.newText  } };
            $http.post( "http://dnotesapp.herokuapp.com/rest/notes", note )
                .then( function(res) {
                    console.log(res);
                    $scope.data.push( res.data.note );
                    removeCssErrorClass();
                }, function(data) {
                    alert('Error!');
            });
        }
        else {
            setCssErrorClass();
        }
    }

    $scope.deleteNote = function( noteId ) {
        console.log( 'Note id: ' + noteId );
        $http.delete( "http://dnotesapp.herokuapp.com/rest/notes" + noteId )
            .then( function(res) {
                console.log(res);
                removeNoteFromArray( noteId );
            }, function(res) {
                alert( 'Error!' );
            });
    }

    $scope.clear = function() {
        $scope.newTitle = "";
        $scope.newText = "";
        removeCssErrorClass();
    }

    var removeNoteFromArray = function( noteId ) {
        for( var i = 0; i < $scope.data.length; i++ ) {
            if( $scope.data[i].id == noteId ) {
                $scope.data.splice( i, 1 );
            }
        }
    }

    var setCssErrorClass = function() {
        addCssClass( "title-note-field", "has-error has-feedback" );
        removeCssClass( "title-note-fail-icon", "hidden" );
        removeCssClass( "title-error-label", "hidden" );
    }

    var removeCssErrorClass = function() {
        removeCssClass( "title-note-field", "has-error" );
        removeCssClass( "title-note-field", "has-feedback" );
        addCssClass( "title-note-fail-icon", "hidden" );
        addCssClass( "title-error-label", "hidden" );
    }

    var addCssClass = function( id, cssClass ) {
        document.getElementById( id ).className += " " + cssClass;
    }

    var removeCssClass = function( id, cssClass ) {
        document.getElementById( id ).classList.remove( cssClass );
    }

    //On page load;
    $scope.retrieveNotes();

}]);
