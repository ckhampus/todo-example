var React = require('react');
var Task = require('../task');

var TaskList = React.createClass({
    render: function () {
        var taskNodes = this.props.tasks.map(function (task) {
            return (
                <Task completed={task.completed}>{task.description}</Task>
            );
        });
        return (
            <div className="taskList">
                {taskNodes}
            </div>
        );
    }
});

module.exports = TaskList;