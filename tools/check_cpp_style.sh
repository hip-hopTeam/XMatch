#!/bin/bash

THIS_DIR=$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )

ROOT_DIR=$THIS_DIR/..

return_status=0

function run_cpplint() {
    # $1 is directory
    # $2 is root
    python $THIS_DIR/cpplint.py --root=$2 $( find $1 -name \*.h -or -name \*.cpp )
    return_status=$(($return_status || $?))
}

run_cpplint $ROOT_DIR/tools/example example

exit $return_status