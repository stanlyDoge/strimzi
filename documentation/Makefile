PROJECT_NAME=documentation

BUILD_PATH ?= ../build/$(PROJECT_NAME)

build:
	mkdir -p $(BUILD_PATH)/adoc

	# Copy adoc to build path
	cp -r ./adoc $(BUILD_PATH)

	# Replace image tags
	find $(BUILD_PATH)/adoc/ -name '*.adoc' -type f -exec sed -i '/:revnumber: [a-zA-Z0-9_-.]\+/s/:revnumber: [a-zA-Z0-9_-.]\+/:revnumber: $(RELEASE_VERSION)/' {} \;

	# Generate HTML
	mkdir -p $(BUILD_PATH)/html
	asciidoctor $(BUILD_PATH)/adoc/docu.adoc -o $(BUILD_PATH)/html/master.html

	# Generate noheader HTML
	mkdir -p documentation/htmlnoheader
	asciidoctor -s $(BUILD_PATH)/adoc/docu.adoc -o $(BUILD_PATH)/htmlnoheader/master.html

	# Copy images
	cp -vr $(BUILD_PATH)/adoc/images $(BUILD_PATH)/html/images

	# Delete the adoc dir from the build path
	rm -rf $(BUILD_PATH)/adoc

.PHONY: all build clean docker_build docker_push docker_tag