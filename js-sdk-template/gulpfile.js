const gulp = require("gulp");
const ts = require("gulp-typescript");
const merge = require('merge2');
const rename = require("gulp-rename");
const babel = require('gulp-babel');
const tsProject = ts.createProject("tsconfig.json");

gulp.task('default', function() {
    const tsResult = tsProject.src()
        .pipe(tsProject());

    return merge([
        tsResult.dts.pipe(gulp.dest('dist')),
        tsResult.js.pipe(rename(function (path) {
            path.extname = ".js";
        })).pipe(gulp.dest('dist'))
    ]);
});

gulp.task('b', function() {
     return gulp.src("dist/**/*.js").pipe(babel({
         presets: ['env'],
         "plugins": ["transform-runtime"]
     })).pipe(gulp.dest('www'));
});