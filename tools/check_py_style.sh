#!/bin/bash

THIS_DIR=$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )

ROOT_DIR=$THIS_DIR/..

return_status=0

function run_pylint() {
    # $1 is directory 
    pylint $( find $1 -name \*.py )
    return_status=$(($return_status || $?))
}

run_pylint $ROOT_DIR/tools/example

exit $return_status