var React = require('react');

var Task = React.createClass({
    render: function () {
        return (
            <div className="task">
                <label>
                    <input type="checkbox" checked={this.props.completed}/>{this.props.children}
                </label>
            </div>
        );
    }
});

module.exports = Task;