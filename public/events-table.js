'use strict';

var EventsTable = React.createClass({

  getInitialState: function() {
    return {events: [], buffer: [], loaded: true};
  },

  componentDidMount: function() {
    oboe("http://localhost:8080/rest/v1/events").node("!", function(record) {
        var buffer = this.state.buffer;
        buffer.push(record);
        if(buffer.length === 100) {
            var data = this.state.events.concat(buffer);
            this.setState({events: data, buffer: [] });
        } else {
            this.setState({buffer: buffer, loaded: false});
        }
    }.bind(this)).node("!", function() {
        this.setState({loaded: true})
    }.bind(this));
  },

  shouldComponentUpdate: function(newProps, newState) {
    if(newState.buffer.length === 0) {
        console.log('should update');
        return true;
    }
    return false;
  },

  render: function() {
    var createRow = function(event) {
      return (
        React.createElement('tr', {key: uuid()},
            React.createElement('td', null, event.code),
            React.createElement('td', null, event.title),
            React.createElement('td', null, event.date)
        )
      );
    };
    var createTable = function(events) {
      return (
        React.createElement('table', {className: "table"},
            React.createElement('thead', null,
                React.createElement('tr', null,
                    React.createElement('th', null, "Code"),
                    React.createElement('th', null, "Title"),
                    React.createElement('th', null, "Date produced")
                )
            ),
            React.createElement("tbody", null, events.map(createRow))
        )
      );
    };
    return (
        React.createElement('div', null,
            React.createElement('h3', null, 'Events'),
            React.createElement('div', null, this.state.events.length + ' ' + (this.state.loaded ? '' : ' (loading...)' )),
            React.createElement('div', null, createTable(this.state.events))
        )
    );
  }
});

ReactDOM.render(React.createElement(EventsTable, null), document.getElementById("container"));