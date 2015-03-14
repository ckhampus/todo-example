import React from 'react';
import TaskForm from '../taskform';
import TaskList from '../tasklist';

var TaskBox = React.createClass({
    getInitialState: function() {
        return {tasks: []};
    },
    handleTaskSubmit: function(task) {
        // TODO: submit to the server and refresh the list
    },
    render: function () {
        return (
            <div>
                <TaskForm onTaskSubmit={this.handleTaskSubmit} />
                <TaskList tasks={this.props.tasks} />
            </div>
        );
    }
});

export default TaskBox;