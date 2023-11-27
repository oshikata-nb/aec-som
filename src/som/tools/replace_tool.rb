#!/usr/bin/ruby

require 'tempfile'

filelist = Dir.glob("**/*.java")

filelist.each do |file|

	temp = Tempfile::new("foobar")

	open(file, 'r') do |f|

		while line = f.gets

			if (line =~ /\*\s@author nonagase/) ||
			   (line =~ /\*\s@author morigami/) ||
			   (line =~ /\*\s@author kajihara/) then
			  temp.puts(" * @author jbd")
			else
			  temp.puts(line)
			end
			

		end
	end
	temp.close

	temp.open
	open(file, "w") {|f| temp.each {|line| f.puts(line) }}
	temp.close(true)

end
